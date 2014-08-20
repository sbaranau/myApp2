package by.baranov.sergey;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 *TODO
 */
public final class LoginTag extends TagSupport {
    String userName = null;

    public int doStartTag() throws JspException {
        try {
            if ((userName == null) || ("".equals(userName))) {
                pageContext.getOut().write("Not login");

            } else {
                pageContext.getOut().write("Logged as " + userName);
            }

        } catch (IOException e) {

        }
        return SKIP_BODY;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


}
