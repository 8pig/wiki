package com.zz.wiki.mapper;

import com.zz.wiki.domain.Test;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhou
 */
public interface CustomDocMapper {

    public void incrementViewCount(@Param("id") Long id);
    public void incrementVoteCount(@Param("id") Long id);

}
