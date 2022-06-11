package com.gsnotes.services;

import com.gsnotes.bo.Compte;
import com.gsnotes.bo.Role;
import com.gsnotes.utils.export.ExcelExporter;
import org.springframework.stereotype.Service;

import java.util.List;
import com.gsnotes.bo.Module;


public interface IModuleService {
    public List<Module> getAllModule();
}
