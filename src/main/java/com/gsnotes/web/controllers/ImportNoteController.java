package com.gsnotes.web.controllers;

import com.gsnotes.bo.ModelAtt;
import com.gsnotes.services.*;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/cadreadmin") // Très important car, dans notre Spring security config les URL qui commencent par ADMIN
// sont dédiées à l'admin. Toutes les URL dédinies dans ce controleur définissent des

public class ImportNoteController {
    @Autowired
    IModuleService iModuleService; // On obtient par injection automatique le service

    @Autowired
    private HttpSession httpSession;

    @Autowired
    IFileUploaderService fileService;
    //private String dir="C:/Users/pc/OneDrive - Universite Abdelmalek Essaadi/Desktop/GI2/code/";

    @Autowired
    IExcelDataService excelservice;

    @RequestMapping(value = "ImportNote")
    public String ImportNote(Model model){
        model.addAttribute("ModuleList", iModuleService.getAllModule());
        return "cadreadmin/ImportNote";
    }
    @PostMapping("/uploadFile")
    public String uploadFile(@ModelAttribute("Import_Attribute") ModelAtt modelAtt,@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:/cadreadmin/Problem";
        }
        if(!FilenameUtils.isExtension(file.getOriginalFilename(),"xlsx")) {

            redirectAttributes.addFlashAttribute("message", "Please Select an Excel file");
            return "redirect:/cadreadmin/Problem";}

            // Get the file and save it somewhere
        String path2 = "src/main/webapp/WEB-INF/files";
        File pathAsFile = new File(path2);


        if (!Files.exists(Paths.get(path2))) {
            pathAsFile.mkdir();
        }

        String path=pathAsFile.getAbsolutePath() +"/"+file.getOriginalFilename();
        System.out.println("path is "+path);

        file.transferTo(new File(path));
        List list=excelservice.AllGood(modelAtt,path);
        httpSession.setAttribute("list",list);
        httpSession.setAttribute("att",modelAtt);

        if(!(list==null)){
            if(!excelservice.NotesAlreadyExcisting(modelAtt)){
                redirectAttributes.addFlashAttribute("list", list);
                redirectAttributes.addFlashAttribute("att", modelAtt);
                File fileToDelete = new File(path);
                fileToDelete.delete();
                return "redirect:/cadreadmin/ConfirmUpdate";
            }
            excelservice.Import(modelAtt,list);
        }else{
            File fileToDelete = new File(path);
            boolean success = fileToDelete.delete();
            redirectAttributes.addFlashAttribute("message",
                    "Importation problem of'" + file.getOriginalFilename() + "'");
            File fileToDelete2 = new File(path);
            fileToDelete.delete();
            return "redirect:/cadreadmin/Problem";

        }




            //    redirectAttributes.addFlashAttribute("Import_Attribute", modelAtt);


        return "redirect:/cadreadmin/ImportNote";}

   @RequestMapping(value = "ConfirmUpdate")
    public String success(){
       return "cadreadmin/ConfirmUpdate";
    }


    @RequestMapping(value = "Confirm")
    public String confirm(){
        excelservice.Import((ModelAtt) httpSession.getAttribute("att"), (List) httpSession.getAttribute("list"));

        return "redirect:/cadreadmin/ImportNote";    }


    @RequestMapping(value = "Problem")
    public String problem(@ModelAttribute("message") String mesaage,Model model){
        model.addAttribute("messege", mesaage);
        return "cadreadmin/ImportationProblem";}

}
