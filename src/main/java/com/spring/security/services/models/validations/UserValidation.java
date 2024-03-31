package com.spring.security.services.models.validations;

import com.spring.security.persistence.entities.UserEntity;
import com.spring.security.services.models.dtos.ResponseDTO;

public class UserValidation {
    public ResponseDTO validate(UserEntity user) {
        ResponseDTO response = new ResponseDTO();

        response.setNumOfErrors(0);

        if (user.getFirstName() == null ||
            user.getFirstName().length() < 3 ||
            user.getFirstName().length() > 15
        ) {
            response.setNumOfErrors(response.getNumOfErrors() + 1);
            response.setMessage("the field firstname cannot be null, less three characters or more than fiveteen characters");
        }

        if (user.getLastName() == null ||
            user.getLastName().length() < 3 ||
            user.getLastName().length() > 30
        ) {
            response.setNumOfErrors(response.getNumOfErrors() + 1);
            response.setMessage("the field lastname cannot be null, les three characters or more than tirty characters");
        }

        if (user.getEmail() == null ||
            !user.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
        ) {
            response.setNumOfErrors(response.getNumOfErrors() + 1);
            response.setMessage("the field email is not valid");
        }

        if (user.getPassword() == null ||
            !user.getPassword().matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,32}$")
        ) {
            response.setNumOfErrors(response.getNumOfErrors() + 1);
            response.setMessage("the field need one number, one mayus letter and minus letter and the length is between 8 and 32");
        }

        return response;
    }
}
