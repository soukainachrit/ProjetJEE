package com.gsnotes.services.impl;

import com.gsnotes.dao.IModuleDao;
import com.gsnotes.services.IModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gsnotes.bo.Module;

import java.util.List;
@Service
@Transactional
public class ModuleServiceImpl implements IModuleService {

    @Autowired
    private IModuleDao iModuleDao ;

    @Override
    public List<Module> getAllModule() {
        return iModuleDao.findAll();
    }
}
