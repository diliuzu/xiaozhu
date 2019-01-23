package com.xzlx.web.api.service.impl;

import com.xzlx.commons.util.dto.BaseResult;
import com.xzlx.commons.util.persistance.BasePageDTO;
import com.xzlx.commons.util.validator.BeanValidator;
import com.xzlx.domain.*;
import com.xzlx.web.api.dao.*;
import com.xzlx.web.api.service.TbTravelService;
import com.xzlx.web.api.web.dto.TbTravelDTO;
import com.xzlx.web.api.web.dto.TravelInfoDTO;
import com.xzlx.web.api.web.dto.UserDTO;
import com.xzlx.web.api.web.dto.UserInfoDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class TbtravelServiceImpl implements TbTravelService {

    @Autowired
    TbTravelDao tbTravelDao;

    @Autowired
    TbTagDao tbTagDao;

    @Autowired
    TbCollectionDao tbCollectionDao;

    @Autowired
    TbPraiseDao tbPraiseDao;

    @Autowired
    TbUserDao tbUserDao;
    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public BaseResult selectById(Integer id, TbUser user) {
        TbTravel tbTravel = tbTravelDao.selectById(id);
        BaseResult baseResult = null;
        if(tbTravel!=null){
            //游览量+1
            tbTravel.setClicknum(tbTravel.getClicknum()+1);
            tbTravelDao.update(tbTravel);
            TravelInfoDTO travelInfoDTO = new TravelInfoDTO();
            BeanUtils.copyProperties(tbTravel,travelInfoDTO);
            Map<String,Object> m = new HashMap<>();
            m.put("tbTravel",tbTravel);
            m.put("tbUser",user);
            UserInfoDTO userInfoDTO = tbUserDao.selectByUid(m);
            List<TbTaglib> tags = tbTagDao.selectByTargetId(tbTravel);
            travelInfoDTO.setTag(tags);
            if(user==null||user.getId()==null){
                travelInfoDTO.setIsPraised(false);
                travelInfoDTO.setIsCollected(false);
                userInfoDTO.setFollow(false);
            }
            else{
                TbCollection tbCollection = tbCollectionDao.selectByUid(m);
                TbPraise tbPraise = tbPraiseDao.selectByUid(m);
                if(tbTravel.getId().equals(tbCollection.getTargetId())){
                    travelInfoDTO.setIsCollected(true);
                }else {
                    travelInfoDTO.setIsCollected(false);
                }
                if(tbTravel.getId().equals(tbPraise.getTargetId())){
                    travelInfoDTO.setIsPraised(true);
                }
                else {
                    travelInfoDTO.setIsPraised(false);
                }
            }
            travelInfoDTO.setAuthor(userInfoDTO);
            baseResult = BaseResult.success("成功",travelInfoDTO);
        }
        else {
            baseResult = BaseResult.fail("查询没有结果");
        }
        return baseResult;
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public BaseResult delete(Integer id) {
        tbTravelDao.selectById(id);
        return BaseResult.success("删除成功");
    }

    /**
     * 新增和更新
     * @param tbTravel
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public BaseResult save(TbTravel tbTravel) {

        String validator = BeanValidator.validator(tbTravel);

         BaseResult baseResult = null;
        if(validator==null){
            if(tbTravel.getId()==null){
                //新增
                tbTravel.setCreated(new Date());
                tbTravel.setUpdated(new Date());
                tbTravel.setCollectnum(0);
                tbTravel.setPraisenum(0);
                tbTravel.setClicknum(0);
                tbTravel.setHot(0);
                tbTravel.setOfficial(0);
                tbTravelDao.insert(tbTravel);
                baseResult = BaseResult.success("新增成功");
            }
            else{
                //修改
                tbTravel.setUpdated(new Date());
                tbTravelDao.update(tbTravel);
                baseResult = BaseResult.success("修改成功");
            }
        }
        else {
            baseResult = BaseResult.fail(validator);
        }
        return baseResult;
    }

    /**
     * 分页数据
     * @param tbTravel
     * @return
     */
    @Override
    public BaseResult page(int page, int pageSize, TbTravel tbTravel, TbUser user) {
        BasePageDTO<TbTravelDTO> pageDTO = new BasePageDTO<>();
        Map<String, Object> map = new HashMap<>();
        BaseResult baseResult = null;
        map.put("start", page*pageSize);
        map.put("length",pageSize);
        map.put("tbTravel",tbTravel);
        Integer totalPage = tbTravelDao.count(tbTravel);
        List<TbTravel> tbTravels = tbTravelDao.selectByPage(map);
        //判断点赞收藏
        List<TbTravelDTO> tbTravelDTOs = collAndPre(tbTravels, user);
        pageDTO.setPage(page);
        pageDTO.setPageSize(pageSize);
        pageDTO.setTotalPage(totalPage);
        pageDTO.setEntity(tbTravelDTOs);
        if(tbTravel != null){
            baseResult = BaseResult.success("搜索成功",pageDTO);
        }else {
            baseResult = BaseResult.fail("搜索失败");
        }
        return baseResult;
    }

    /**
     * 标签查询分页
     * @param page
     * @param pageSize
     * @param tbTag
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public BaseResult pageByTag(int page,int pageSize,TbTag tbTag, TbUser user) {

        List<TbTag> tbTags = tbTagDao.selectByTagId(tbTag);
        Map<String, Object> map = new HashMap<>();
        map.put("start", page*pageSize);
        map.put("length",pageSize);
        map.put("tbTags",tbTags);
        List<TbTravel> travels = tbTravelDao.selectByTag(map);
        BasePageDTO<TbTravelDTO> pageDTO = new BasePageDTO<>();
        Integer totalPage = tbTagDao.count(tbTag);
        //判断点赞收藏
        List<TbTravelDTO> tbTravelDTOs = collAndPre(travels, user);
        pageDTO.setPage(page);
        pageDTO.setPageSize(pageSize);
        pageDTO.setTotalPage(totalPage);
        pageDTO.setEntity(tbTravelDTOs);
        BaseResult baseResult = null;
        if (travels.size()==0){
            baseResult = BaseResult.fail("查询失败");
        }
        else {

            baseResult = BaseResult.success("查询成功",pageDTO);
        }
        return baseResult;
    }
    /**
     * 判断是否点赞收藏
     * @param tbTravels
     * @param user
     * @return
     */
    public List<TbTravelDTO> collAndPre(List<TbTravel> tbTravels, TbUser user){
        List<TbUser> as = tbUserDao.selectByUids(tbTravels);
        List<TbTravelDTO> tbTravelDTOs = new ArrayList<>();

        if(user==null||user.getId()==null){
            for (TbTravel travel:tbTravels){
                TbTravelDTO tbTravelDTO = new TbTravelDTO();
                BeanUtils.copyProperties(travel, tbTravelDTO);
                tbTravelDTO.setIsPraised(false);
                tbTravelDTO.setIsCollected(false);
                for (TbUser tbUser:as){
                    UserDTO userDTO = new UserDTO();
                    BeanUtils.copyProperties(tbUser, userDTO);
                    tbTravelDTO.setAuthor(userDTO);
                }
                tbTravelDTOs.add(tbTravelDTO);
            }
        }
        else {
            Map<String,Object> m = new HashMap<>();
            m.put("uid",user.getId());
            m.put("tbTravels",tbTravels);
            List<TbCollection> tbCollections = tbCollectionDao.selectByUids(m);
            List<TbPraise> tbPraises = tbPraiseDao.selectByUids(m);
            for (TbTravel travel:tbTravels){
                TbTravelDTO tbTravelDTO = new TbTravelDTO();
                BeanUtils.copyProperties(travel, tbTravelDTO);
                for (TbUser tbUser:as){
                    UserDTO userDTO = new UserDTO();
                    BeanUtils.copyProperties(tbUser, userDTO);
                    tbTravelDTO.setAuthor(userDTO);
                }
                tbTravelDTO.setIsPraised(false);
                tbTravelDTO.setIsCollected(false);
                for(TbCollection tbCollection:tbCollections){
                    if(travel.getId().equals(tbCollection.getTargetId())){
                        tbTravelDTO.setIsCollected(true);
                    }
                }
                for(TbPraise tbPraise:tbPraises){
                    if(travel.getId().equals(tbPraise.getTargetId())){
                        tbTravelDTO.setIsPraised(true);
                    }
                }
                tbTravelDTOs.add(tbTravelDTO);
            }
        }
        return tbTravelDTOs;
    }
}
