package com.xzlx.web.api.service.impl;

import com.xzlx.commons.util.dto.BaseResult;
import com.xzlx.domain.TbUserPhoto;
import com.xzlx.web.api.dao.TbUserPhotoDao;
import com.xzlx.web.api.service.TbUserPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TbUserPhotoServiceImpl implements TbUserPhotoService {
@Autowired
    private TbUserPhotoDao tbUserPhotoDao;
    @Override
    public BaseResult getphoto() {
        BaseResult baseResult=null;
        List<TbUserPhoto> getphoto = tbUserPhotoDao.getphoto();
         baseResult=baseResult.success("成功",getphoto);
        return baseResult;
    }
}
