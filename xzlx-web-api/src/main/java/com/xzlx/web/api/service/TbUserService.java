package com.xzlx.web.api.service;

import com.xzlx.commons.util.dto.BaseResult;
import com.xzlx.domain.TbCollection;
import com.xzlx.domain.TbUser;
import org.apache.commons.mail.EmailException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Service
public interface TbUserService {
    BaseResult login(TbUser tbUser);
    BaseResult register(TbUser tbUser);
    BaseResult forgetpassword(TbUser tbUser, HttpServletRequest httpServletRequest) throws EmailException;
    BaseResult personal(TbUser tbUser);
    BaseResult checkcode(String code, HttpServletRequest httpServletRequest);
    BaseResult resetpassword(TbUser tbUser);
    BaseResult edit(TbUser tbUser);
    List<TbCollection> mycollection(int id, int typeid);

    BaseResult collect(Integer travelId, Integer id, Integer type);
    BaseResult praise(Integer travelId, Integer id, Integer type);
}
