package com.xzlx.web.api.dao;

import com.xzlx.web.api.web.dto.TbCommentDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TbCommentDAO {
    List<TbCommentDTO> selectComment(@Param("id")int id, @Param("start")int start,@Param("length") int length,@Param("tbName")String tbName);

    Integer selectCommentCounts(@Param("id")Integer id,@Param("tbName")String tbName);

    void insert(@Param("id")Integer travelId, @Param("tbCommentDTO")TbCommentDTO tbCommentDTO, @Param("tbName")String tbName,@Param("targetName") String targetName);
}
