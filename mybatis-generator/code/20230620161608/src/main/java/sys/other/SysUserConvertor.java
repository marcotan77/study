package .sys.other;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import .sys.entity.SysUserDO;

@Mapper(componentModel = "spring")
public interface SysUserConvertor {
    SysUserConvertor INSTANCE = Mappers.getMapper(SysUserConvertor.class);

    SysUserDO toSysUserDO(SysUserQuery SysUserQuery);

    SysUserDO tSysUserDO(SysUserDTO SysUserDTO);

    SysUserDTO toSysUserDTO(SysUserDO SysUserDO);

    List<SysUserDO> toSysUserDOList(List<SysUserDTO> SysUserDTOList);

    List< SysUserDTO> toSysUserDTOList(List<BaseUserDO>  SysUserDTOList);
}