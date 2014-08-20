package by.baranov.sergey;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.validation.constraints.Size;

/**
 *TODO
 */
public class UserVar {


    @NotEmpty(message = "shouldn't be empty")
    @Size(min = 3, max = 20, message = "should be from 3 to 20 signs")
    String name;

    @NotEmpty(message = "shouldn't be empty")
    @Size(min = 3, max = 20, message = "should be from 3 to 20 signs")
    String password;

    @NotEmpty(message = "shouldn't be empty")
    @Size(min = 3, max = 20, message = "should be from 3 to 20 signs")
    String comPassword;

    @NotEmpty(message = "shouldn't be empty")
    @Email(message = "should be Email")
    String email;

    @NotEmpty(message = "shouldn't be empty")
    @Email(message = "should be Email")
    String comEmail;

    private CommonsMultipartFile fileData;

    public CommonsMultipartFile getFileData() {
        return fileData;
    }

    public void setFileData(CommonsMultipartFile fileData) {
        this.fileData = fileData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getComPassword() {
        return comPassword;
    }

    public void setComPassword(String comPassword) {
        this.comPassword = comPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComEmail() {
        return comEmail;
    }

    public void setComEmail(String comEmail) {
        this.comEmail = comEmail;
    }

    public UserVar() {
    }

    public UserVar(String name, String password, String comPassword, String email, String comEmail) {
        this.name = name;
        this.password = password;
        this.comPassword = comPassword;
        this.email = email;
        this.comEmail = comEmail;
    }
}
