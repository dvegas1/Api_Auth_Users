package com.microservices.services.implementations;

import com.microservices.components.configurations.AppSettingsExternalConfigurations;
import com.microservices.components.helpers.JwtUtil;
import com.microservices.constants.ManagementGeneralConstants;
import com.microservices.dtos.commons.DetailsUserDto;
import com.microservices.dtos.commons.StatusUser;
import com.microservices.exceptions.InformationAuthException;
import com.microservices.exceptions.ValidationErrors;
import com.microservices.repository.contracts.PhoneRepository;
import com.microservices.repository.contracts.UserRepository;
import com.microservices.repository.dto.UserPhonesDTO;
import com.microservices.repository.dto.UsersDTO;
import com.microservices.repository.entities.PhonesEntity;
import com.microservices.repository.entities.UserEntity;
import com.microservices.services.contracts.IAuditService;
import com.microservices.services.contracts.IAuthService;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * CardService
 * <p>
 * CardService class.
 * <p>
 * ESTE COMPONENTE FUE CONSTRUIDO SIGUIENDO LOS ESTANDARES DE DESARROLLO Y EL PROCEDIMIENTO
 * DE DESARROLLO DE APLICACIONES..
 *
 * @author Darwin Vegas.
 * @author darwinvegas1@gmail.com
 * @since 21/04/2024
 */
@SuppressFBWarnings("EC_UNRELATED_TYPES")
@Service
@Transactional
@SuppressWarnings("ALL")
public class AuthService implements IAuthService {
    private static final Logger LOGGER = LogManager.getLogger(AuthService.class);
    public static final String CLIENTE = "NISUM";
    public static final String NO_APLICA = "NO_APLICA";
    public static final String KEY_VALUE_SEPARATOR = "\":\"";
    public static final String DATE_CONSTANT = "date";
    public static final String MOUNT_CONSTANT = "mount";
    public static final String CARD_TYPE_CONSTANT = "type";
    UserRepository userRepository;
    PhoneRepository phoneRepository;
    IAuditService auditService;
    AppSettingsExternalConfigurations appSettingsExternalConfigurations;

    @SuppressFBWarnings("EI_EXPOSE_REP2")
    @Autowired
    public AuthService(
        UserRepository userRepository, PhoneRepository phoneRepository, IAuditService auditService, AppSettingsExternalConfigurations appSettingsExternalConfigurations) {
        this.userRepository = userRepository;
        this.auditService = auditService;
        this.phoneRepository = phoneRepository;
        this.appSettingsExternalConfigurations = appSettingsExternalConfigurations;
    }

    @Override
    public StatusUser createUser(String name, String email, String password, ArrayList<UserPhonesDTO> phones) throws InformationAuthException, ParseException, ValidationErrors {
        LOGGER.info("Execute createUser");

        var searchEmail = userRepository.findByEmail(email).orElse(null);

        if (!Objects.isNull(searchEmail)) {
            LOGGER.error("The email [{}]  already exist", email);
            auditService.saveAdmconsLogAudit(email, CLIENTE, ManagementGeneralConstants.OPERATION_CREATEUSER, ManagementGeneralConstants.EMAIL_FOUND_CODE, ManagementGeneralConstants.EMAIL_FOUND_MESSAGE);
            return StatusUser.builder().message(ManagementGeneralConstants.EMAIL_FOUND_MESSAGE).code(ManagementGeneralConstants.EMAIL_FOUND_CODE).build();
        }

        UserEntity createUserResponse = userRepository.save(UserEntity.builder().name(name).email(email).build());

        if (Objects.isNull(createUserResponse)) {
            LOGGER.error("The User [{}]  not created", email);
            auditService.saveAdmconsLogAudit(email, CLIENTE, ManagementGeneralConstants.OPERATION_CREATEUSER, ManagementGeneralConstants.USER_NOT_CREATE_CODE, ManagementGeneralConstants.USER_NOT_CREATE_MESSAGE);
            return StatusUser.builder().message(ManagementGeneralConstants.USER_NOT_CREATE_MESSAGE).code(ManagementGeneralConstants.USER_NOT_CREATE_CODE).build();
        }

        if (Objects.nonNull(phones)) {
            phoneRepository.saveAll(phones.stream()
                .filter(phone -> !(phone.getNumber().isEmpty() && phone.getCitycode().isEmpty() && phone.getContrycode().isEmpty()))
                .map(phone -> PhonesEntity.builder()
                    .citycode(phone.getCitycode())
                    .contrycode(phone.getContrycode())
                    .idUser(createUserResponse.getId())
                    .number(phone.getNumber())
                    .build())
                .collect(Collectors.toList()));
        }

        UsersDTO finUser = new UsersDTO();
        List<Object[]> resultList = userRepository.findUser(createUserResponse.getId());
        ArrayList<UserPhonesDTO> allPhonesUser = new ArrayList<>();
        LOGGER.info("resultList:{}", resultList);
        if (!resultList.isEmpty()) {
            for (Object[] result : resultList) {
                UserPhonesDTO userPhoneDto = new UserPhonesDTO();

                if (Objects.nonNull(result[0])) {
                    UserEntity user = (UserEntity) result[0];
                    finUser = finUser.builder().id(user.getId()).name(user.getName()).email(user.getEmail()).created(String.valueOf(user.getCreated())).modified(String.valueOf(user.getModified())).last_login(String.valueOf(user.getLast_login())).active(user.getActive()).build();
                }

                if (Objects.nonNull(result[1])) {
                    PhonesEntity phone = (PhonesEntity) result[1];

                    userPhoneDto.setNumber(phone.getNumber());
                    userPhoneDto.setCitycode(phone.getCitycode());
                    userPhoneDto.setContrycode(phone.getContrycode());
                }

                allPhonesUser.add(userPhoneDto);
            }
        }


        String token = JwtUtil.generateToken(email);

        auditService.saveAdmconsLogAudit(email, CLIENTE, ManagementGeneralConstants.OPERATION_CREATEUSER, ManagementGeneralConstants.USER_CREATE_SUCESS_CODE, ManagementGeneralConstants.USER_CREATE_SUCESS_MESSAGE);


        return StatusUser.builder().message(ManagementGeneralConstants.USER_CREATE_SUCESS_MESSAGE).code(ManagementGeneralConstants.USER_CREATE_SUCESS_CODE).user(DetailsUserDto.builder().id(finUser.getId().toString()).created(String.valueOf(finUser.getCreated())).modified(String.valueOf(finUser.getModified())).last_login(String.valueOf(finUser.getLast_login())).token(token).isactive((finUser.isActive()) ? "YES" : "NO").phones(allPhonesUser).build()).build();
    }
}
