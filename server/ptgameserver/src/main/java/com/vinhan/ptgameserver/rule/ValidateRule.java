/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinhan.ptgameserver.rule;

import com.vinhan.ptgameserver.mapclass.User;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.stereotype.Component;

/**
 *
 * @author Qui
 */
@Component
public class ValidateRule {

    public boolean isUserValid(User user) {
        boolean isValid = false;
        if (StringUtil.isBlank(user.getUserName())
                || StringUtil.isBlank(user.getPassWord())
                || StringUtil.isBlank(user.getUrlMap())) {
            isValid = false;
        } else {
            isValid = true;
        }
        return isValid;
    }
}
