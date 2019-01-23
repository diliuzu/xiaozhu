package com.xzlx.web.api.dao;

import com.xzlx.domain.TbCollection;
import com.xzlx.domain.TbTravel;
import com.xzlx.domain.TbUser;
import com.xzlx.web.api.web.dto.UserInfoDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface TbUserDao {
    TbUser login(TbUser tbUser);
    void register(TbUser tbuser);
    TbUser personal(TbUser tbUser);
    void resetpassword(TbUser tbUser);
    void edit(TbUser tbUser);
    List<TbCollection> mycollection(Map<String, Object> map);

    Integer selectCollect(@Param("targetId") Integer travelId, @Param("userId")Integer id, @Param("typeId")Integer type);

    Integer selectPraise(@Param("targetId") Integer travelId, @Param("userId")Integer id, @Param("typeId")Integer type);

    void addCollect(@Param("targetId") Integer travelId, @Param("userId")Integer id, @Param("typeId")Integer type);

    void deleteCollect(@Param("targetId") Integer travelId, @Param("userId")Integer id, @Param("typeId")Integer type);

    void upCollectCount(@Param("id") Integer travelId,@Param("typeId") Integer id,@Param("table") String table);

    void downCollectCount(@Param("id") Integer travelId,@Param("typeId")Integer id,@Param("table") String table);

    void addPraise(@Param("targetId") Integer travelId, @Param("userId")Integer id, @Param("typeId")Integer type);

    void deletePraise(@Param("targetId") Integer travelId, @Param("userId")Integer id, @Param("typeId")Integer type);


    List<TbUser> selectByUids(List<TbTravel> tbTravels);

    UserInfoDTO selectByUid(Map<String,Object> map);

    TbUser selectall();
}
