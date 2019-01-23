package com.xzlx.web.api.dao;

import com.xzlx.domain.TbUserPhoto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbUserPhotoDao {
    List<TbUserPhoto> getphoto();
}
