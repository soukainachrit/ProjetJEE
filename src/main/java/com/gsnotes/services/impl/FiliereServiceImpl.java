package com.gsnotes.services.impl;

import com.gsnotes.dao.FiliereDao;
import com.gsnotes.dao.IModuleDao;
import com.gsnotes.dao.NiveauDao;
import com.gsnotes.services.iFiliereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




@Service
@Transactional
public class FiliereServiceImpl implements iFiliereService {
    @Autowired
    IModuleDao iModuleDao;
    @Autowired
    NiveauDao niveauDao;
    @Autowired
    FiliereDao filiereDao;
    @Override
    public int getSeuilByIdModule(Long id) {
        return iModuleDao.getById(id).getNiveau().getFiliere().getSeuil();
    }
}
