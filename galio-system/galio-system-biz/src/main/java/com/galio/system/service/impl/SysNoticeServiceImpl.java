package com.galio.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.galio.system.model.SysNotice;
import com.galio.system.service.ISysNoticeService;
import com.galio.system.mapper.SysNoticeMapper;
import org.springframework.stereotype.Service;

/**
* @author txyan
* @description 针对表【sys_notice(通知公告表)】的数据库操作Service实现
* @createDate 2023-02-15 21:13:37
*/
@Service
public class SysNoticeServiceImpl extends ServiceImpl<SysNoticeMapper, SysNotice>
    implements ISysNoticeService{

}




