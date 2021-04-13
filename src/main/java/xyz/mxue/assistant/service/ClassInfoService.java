package xyz.mxue.assistant.service;

import xyz.mxue.assistant.entity.ClassInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.mxue.assistant.model.vo.ChangeClassInfoVO;

import java.util.List;

/**
 * <p>
 * 班级 服务类
 * </p>
 *
 * @author mxuexxmy
 * @since 2021-04-08
 */
public interface ClassInfoService extends IService<ClassInfo> {

    List<ChangeClassInfoVO> getClassInfoList(Long userId);

    List<ClassInfo> getMyCreateClass(Long id, long loginIdAsLong);

    List<ClassInfo> getMyJoinClass(Long id, long loginIdAsLong);

    ClassInfo getClassInfoByRelatedId(Long relatedId);

    Long getClassIdByStudent(long loginIdAsLong);
}
