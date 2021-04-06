package xyz.mxue.assistant.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.system.*;
import org.springframework.stereotype.Service;
import xyz.mxue.assistant.model.vo.SysMachineVO;
import xyz.mxue.assistant.service.SysMachineService;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 系统属性监控service接口实现类
 *
 * @author mxuexxmy
 * @date 4/7/2021$ 12:32 AM$
 */
@Service
public class SysMachineServiceImpl implements SysMachineService {
    @Override
    public SysMachineVO query() {
        JvmInfo jvmInfo = SystemUtil.getJvmInfo();
        JavaRuntimeInfo javaRuntimeInfo = SystemUtil.getJavaRuntimeInfo();
        OsInfo osInfo = SystemUtil.getOsInfo();
        HostInfo hostInfo = SystemUtil.getHostInfo();
        RuntimeInfo runtimeInfo = SystemUtil.getRuntimeInfo();
        //系统属性结果集
        SysMachineVO sysMachineVO = new SysMachineVO();

        //系统信息
        SysMachineVO.SysOsInfo sysOsInfo = new SysMachineVO.SysOsInfo();
        sysOsInfo.setOsName(osInfo.getName());
        sysOsInfo.setOsArch(osInfo.getArch());
        sysOsInfo.setOsVersion(osInfo.getVersion());
        sysOsInfo.setOsHostName(hostInfo.getName());
        sysOsInfo.setOsHostAddress(hostInfo.getAddress());
        sysMachineVO.setSysOsInfo(sysOsInfo);

        //Java信息
        SysMachineVO.SysJavaInfo sysJavaInfo = new SysMachineVO.SysJavaInfo();
        sysJavaInfo.setJvmName(jvmInfo.getName());
        sysJavaInfo.setJvmVersion(jvmInfo.getVersion());
        sysJavaInfo.setJvmVendor(jvmInfo.getVendor());
        sysJavaInfo.setJavaName(javaRuntimeInfo.getName());
        sysJavaInfo.setJavaVersion(javaRuntimeInfo.getVersion());
        sysMachineVO.setSysJavaInfo(sysJavaInfo);

        //jvm内存信息
        SysMachineVO.SysJvmMemInfo sysJvmMemInfo = new SysMachineVO.SysJvmMemInfo();
        sysJvmMemInfo.setJvmMaxMemory(FileUtil.readableFileSize(runtimeInfo.getMaxMemory()));
        sysJvmMemInfo.setJvmUsableMemory(FileUtil.readableFileSize(runtimeInfo.getUsableMemory()));
        sysJvmMemInfo.setJvmTotalMemory(FileUtil.readableFileSize(runtimeInfo.getTotalMemory()));
        sysJvmMemInfo.setJvmFreeMemory(FileUtil.readableFileSize(runtimeInfo.getFreeMemory()));
        BigDecimal usedMemory = NumberUtil.sub(new BigDecimal(runtimeInfo.getTotalMemory()), new BigDecimal(runtimeInfo.getFreeMemory()));
        sysJvmMemInfo.setJvmUsedMemory(FileUtil.readableFileSize(usedMemory.longValue()));
        BigDecimal rate = NumberUtil.div(usedMemory, runtimeInfo.getTotalMemory());
        String usedRate = new DecimalFormat("#.00").format(NumberUtil.mul(rate, 100)) + "%";
        sysJvmMemInfo.setJvmMemoryUsedRate(usedRate);
        sysMachineVO.setSysJvmMemInfo(sysJvmMemInfo);
        return sysMachineVO;
    }
}
