package pe.com.cibertec.Matias_Albino_Cristopher_Frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pe.com.cibertec.Matias_Albino_Cristopher_Frontend.viewmodel.BuscarModel;

@Controller
@RequestMapping("/login")

public class BuscarController {

    @GetMapping("/inicio")
    public String inicio(Model model) {
        BuscarModel buscarModel = new BuscarModel("00", "", "","","","","");
        model.addAttribute("buscarModel", buscarModel);
        return "inicio";
    }

    @PostMapping("/buscar")
    public String buscar(@RequestParam("placa") String placa, Model model){

        if(placa == null || placa.trim().length() ==0){
            BuscarModel buscarModel = new BuscarModel("01","Error: de datos","","","","","");
        model.addAttribute("buscarModel",buscarModel);
        return "inicio";
        }

        BuscarModel buscarModel = new BuscarModel("00","","honda","rtx","7","200000","negro");
        model.addAttribute("buscarModel",buscarModel);
        return "principal";
    }
}
