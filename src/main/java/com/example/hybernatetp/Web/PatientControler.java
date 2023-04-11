package com.example.hybernatetp.Web;

import com.example.hybernatetp.Entities.Patient;
import com.example.hybernatetp.Repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientControler {
    private PatientRepository patientRepository;

    @GetMapping("/index")
    public String patients(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "size",defaultValue = "8") int size,
                           @RequestParam(name = "keyword", defaultValue = "") String keyword)
    {
        Page<Patient> patients = patientRepository.findByNomContains(keyword,PageRequest.of(page,size));
        System.out.println("hello :" + patients.getTotalElements());
        model.addAttribute("Patients",patients.getContent());
        model.addAttribute("pages",new int[patients.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "patients";  // Html Page create in template file
    }

    @GetMapping("/delete")
    public String delete(Long id, String keyword, int page){
        patientRepository.deleteById(id);
        return "redirect:/index?"+"page="+page+"&keyword="+keyword;
    }
    @GetMapping("/")
    public String home() {
        return "redirect:/index";
    }
}
