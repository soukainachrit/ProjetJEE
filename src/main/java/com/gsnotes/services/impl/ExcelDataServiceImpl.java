package com.gsnotes.services.impl;

import com.gsnotes.bo.*;

import com.gsnotes.bo.Module;
import com.gsnotes.dao.IModuleDao;
import com.gsnotes.dao.InscDao;
import com.gsnotes.dao.InscModuleDao;
import com.gsnotes.services.IExcelDataService;
import com.gsnotes.services.iFiliereService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@Service
@Transactional
public class ExcelDataServiceImpl implements IExcelDataService {
    @Autowired
    private IModuleDao iModuleDao;

    @Autowired
    private InscDao inscDao;

    @Autowired
    private InscModuleDao inscModuleDao;
    @Autowired
    private iFiliereService iFiliereService;


    Workbook workbook;


    @Override
    public List AllGood(ModelAtt modelAtt, String path) {
        List<String> list = new ArrayList<String>();
        System.out.println("HELLO");

        // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();

        // Create the Workbook
        try {
            FileInputStream excelFile = new FileInputStream(new File(path));
            workbook = new XSSFWorkbook(excelFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Retrieving the number of sheets in the Workbook
        System.out.println("-------Workbook has '" + workbook.getNumberOfSheets() + "' Sheets-----");

        // Getting the Sheet at index zero
        Sheet sheet = workbook.getSheetAt(0);

        // Getting number of columns in the Sheet
        int noOfColumns = sheet.getRow(0).getLastCellNum();
        int noOfColumnsNotes = sheet.getRow(3).getLastCellNum();

        System.out.println("-------Sheet has '" + noOfColumns + "' columns------");

        // Using for-each loop to iterate over the rows and columns
        for (Row row : sheet) {
            for (Cell cell : row) {
                String cellValue = dataFormatter.formatCellValue(cell);
                list.add(cellValue);
            }
        }

        List<String> EtList = createList(list, noOfColumnsNotes);
        HashMap<Long, List<String>> map = Verification(EtList, modelAtt, noOfColumnsNotes, list);
        if (map == null) {
            return null;
        }
        HashMap<Long, List> M_V = VerifyMoyenneAndValidation(sheet, modelAtt);

        // int nbElements = modelAtt.getModule().getElements().size();
        try {
            workbook.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        List l = new ArrayList();
        if (map != null && M_V != null) {
            l.add(map);
            l.add(M_V);
            return l;
        }
        return null;
    }

    @Override
    public Boolean NotesAlreadyExcisting(ModelAtt modelAtt) {
        List<InscriptionModule> InscModule = inscModuleDao.getInscriptionModulesByModule_IdModule(modelAtt.getModule().getIdModule());
        int c = 0;
        for (InscriptionModule i : InscModule) {
            if (SessionManip(modelAtt).equals("Normale")) {
                if (!(i.getNoteSN() == 0))
                    c++;
            }
            if (SessionManip(modelAtt).equals("Rattrapage")) {
                if (!(i.getNoteSR() == 0))
                    c++;

            }
        }
        if (c == 0) {
            return true;
        }
        return false;
    }

    @Override
    public void Import(ModelAtt modelAtt, List l) {
        HashMap<Long, List<String>> map = (HashMap<Long, List<String>>) l.get(0);
        HashMap<Long, List> M_V = (HashMap<Long, List>) l.get(1);

        for (Long id : map.keySet()) {
            System.out.println("YESSSSSS");
            System.out.println("MAP :" + M_V);
        }
        for (Long id : M_V.keySet()) {

            if (SessionManip(modelAtt).equals("Normale")) {
                inscModuleDao.getInscriptionModuleByInscriptionAnnuelle_Etudiant_IdUtilisateurAndAndModule_IdModule(id, modelAtt.getModule().getIdModule()).setNoteSN((Double) M_V.get(id).get(0));
                int j = 0;
                for (String s : map.get(id)) {
                    inscDao.getInscriptionMatiereByInscriptionAnnuelle_Etudiant_IdUtilisateurAndMatiere_IdMatiere(id, modelAtt.getModule().getElements().get(j).getIdMatiere()).setNoteSN(Double.valueOf(s));
                    j++;
                }

                if (M_V.get(id).get(1).equals("V")) {
                    int k = 0;
                    for (String s : map.get(id)) {
                        inscDao.getInscriptionMatiereByInscriptionAnnuelle_Etudiant_IdUtilisateurAndMatiere_IdMatiere(id, modelAtt.getModule().getElements().get(k).getIdMatiere()).setNoteFinale(Double.valueOf(s));
                        k++;
                    }
                    inscModuleDao.getInscriptionModuleByInscriptionAnnuelle_Etudiant_IdUtilisateurAndAndModule_IdModule(id, modelAtt.getModule().getIdModule()).setNoteFinale((Double) M_V.get(id).get(0));
                    inscModuleDao.getInscriptionModuleByInscriptionAnnuelle_Etudiant_IdUtilisateurAndAndModule_IdModule(id, modelAtt.getModule().getIdModule()).setValidation((String) M_V.get(id).get(1));


                }
            } else {
                int m = 0;
                for (String s : map.get(id)) {
                    inscDao.getInscriptionMatiereByInscriptionAnnuelle_Etudiant_IdUtilisateurAndMatiere_IdMatiere(id, modelAtt.getModule().getElements().get(m).getIdMatiere()).setNoteSR(Double.valueOf(s));
                    inscDao.getInscriptionMatiereByInscriptionAnnuelle_Etudiant_IdUtilisateurAndMatiere_IdMatiere(id, modelAtt.getModule().getElements().get(m).getIdMatiere()).setNoteFinale(Double.valueOf(s));
                    m++;
                }
                inscModuleDao.getInscriptionModuleByInscriptionAnnuelle_Etudiant_IdUtilisateurAndAndModule_IdModule(id, modelAtt.getModule().getIdModule()).setValidation((String) M_V.get(id).get(1));
                inscModuleDao.getInscriptionModuleByInscriptionAnnuelle_Etudiant_IdUtilisateurAndAndModule_IdModule(id, modelAtt.getModule().getIdModule()).setNoteSR((Double) M_V.get(id).get(0));
                inscModuleDao.getInscriptionModuleByInscriptionAnnuelle_Etudiant_IdUtilisateurAndAndModule_IdModule(id, modelAtt.getModule().getIdModule()).setNoteFinale((Double) M_V.get(id).get(0));


            }

        }

    }


    @Override
    public HashMap<Long, List<String>> Verification(List<String> l, ModelAtt modelAtt, int noOfColumns, List<String> excelData) {
        String Enseigant = "";
        TreeSet<String> teachers = new TreeSet<>();

        for (Element elem : ModuleManip(modelAtt).getElements()) {
            String ens = elem.getCurrentEnseignant().getPrenom() + " " + elem.getCurrentEnseignant().getNom();
            teachers.add(ens);
        }
        for (String s : teachers) {
            if (s.equals(teachers.last())) {
                Enseigant = Enseigant + s;

            } else {
                Enseigant = Enseigant + s + " et ";
            }
        }


        if (l.get(0).equals("Module") && l.get(1).equals(ModuleManip(modelAtt).getTitre())
                && l.get(2).equals("Semestre") && l.get(3).equals(ModuleManip(modelAtt).getSemestre().getTitre())
                && l.get(4).equals("Année") && l.get(5).equals("2021/2022")
                && l.get(8).equals("Enseignant") && l.get(9).equals(Enseigant) && l.get(10).equals("Session")
                && l.get(11).equals(SessionManip(modelAtt)) && l.get(12).equals("Classe")
                && l.get(13).equals(ModuleManip(modelAtt).getNiveau().getAlias())
        ) {
            int i = 20;

            for (Element elem : ModuleManip(modelAtt).getElements()) {
                if (l.get(i).equals(elem.getNom())) {
                    i++;
                }
            }
            int Nelem = ModuleManip(modelAtt).getElements().size();
            HashMap<Long, List<String>> map = new HashMap<>();
            int k = 3 * noOfColumns;
            do {
                List<String> Notes = new ArrayList<>();

                for (int j = 0; j < Nelem; j++) {
                    Notes.add(l.get(k + 4 + j));
                }
                map.put(Long.valueOf(l.get(k)), Notes);
                k = k + (noOfColumns);

            } while (k < l.size());


            int Count2 = 0;
            for (List<String> list : map.values()) {
                int count1 = 0;
                for (String s : list) {
                    if (Double.valueOf(s) <= 20.00 && Double.valueOf(s) >= 0) {
                        count1++;
                    }

                }
                if (count1 == list.size()) {
                    Count2++;
                }
            }

            if (i == 20 + Nelem && Count2 == map.size()) {
                System.out.println("everything id GOOOOOOOOD");
                return map;
            }

        }

        return null;

    }

    public HashMap<Long, List> VerifyMoyenneAndValidation(Sheet sheet, ModelAtt modelAtt) {

        DataFormatter dataFormatter = new DataFormatter();


        HashMap<Long, List> map = new HashMap<>();

        for (int i = 3; i <= sheet.getLastRowNum(); i++) {
            int size = modelAtt.getModule().getElements().size();
            int nb = size;

            Row row = sheet.getRow(i);
            String id = dataFormatter.formatCellValue(row.getCell(0));

            //formule de calcul de la moyenne
            String formula = "";
            char colName = 'E';

            for (Element element : modelAtt.getModule().getElements()) {
                formula += (colName++) + "" + (i + 1) + "*" + element.getCurrentCoefficient();
                if ((--size) > 0) {
                    formula += "+";
                }
            }
            //formule de validation
            String validationFormula = "IF(" + colName + "" + (i + 1) + ">=" + iFiliereService.getSeuilByIdModule(ModuleManip(modelAtt).getIdModule()) + ("Normale".equals(SessionManip(modelAtt)) ? ",\"V\",\"R\")" : ",\"V\",\"NV\")");

            Cell cellMoy = row.getCell(3 + nb + 1);
            Cell cellVal = row.getCell(3 + nb + 2);

            List li = new ArrayList();
            if (cellMoy.getCellFormula().equals(formula) && cellVal.getCellFormula().equals(validationFormula)) {
                li.add(cellMoy.getNumericCellValue());
                li.add(cellVal.getStringCellValue());

                map.put(Long.valueOf(id), li);
            }
        }
        if (map.size() > 0)
            return map;
        return null;
    }


    public List<String> createList(List<String> excelData, int noOfColumns) {

        ArrayList<String> InfoList = new ArrayList<>();


        int i = 0;
        do {
            InfoList.add(excelData.get(i));
            InfoList.add(excelData.get(i + 1));
            InfoList.add(excelData.get(i + 2));
            InfoList.add(excelData.get(i + 3));
            InfoList.add(excelData.get(i + 4));
            InfoList.add(excelData.get(i + 5));
            InfoList.add(excelData.get(i + 6));
            InfoList.add(excelData.get(i + 7));

            i = i + (noOfColumns);

        } while (i < excelData.size());
        System.out.println(InfoList);

        return InfoList;
    }


    public Module ModuleManip(ModelAtt modelAtt) {
        return iModuleDao.getById(modelAtt.getModule().getIdModule());
    }

    public String SessionManip(ModelAtt modelAtt) {
        if (modelAtt.getSession().equals("1")) {
            return "Normale";
        } else {
            return "Rattrapage";
        }
    }





}
