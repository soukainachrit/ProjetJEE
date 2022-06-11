package com.gsnotes.services;
import com.gsnotes.bo.Etudiant;
import com.gsnotes.bo.InscriptionMatiere;
import com.gsnotes.bo.ModelAtt;
import com.gsnotes.bo.Module;
import org.apache.poi.ss.usermodel.Sheet;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;



public interface IExcelDataService {

    Module ModuleManip(ModelAtt modelAtt);
    String SessionManip(ModelAtt modelAtt);

//    List<String> getExcelDataAsList(ModelAtt modelAtt, String path);
    List AllGood(ModelAtt modelAtt, String path);
    Boolean NotesAlreadyExcisting(ModelAtt modelAtt);
    void Import(ModelAtt modelAtt, List l);
    HashMap<Long,List<String>> Verification(List<String> l, ModelAtt modelAtt, int noOfColumns, List<String> excelData);
    public HashMap<Long, List> VerifyMoyenneAndValidation(Sheet sheet, ModelAtt modelAtt);
}
