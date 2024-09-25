package pe.com.cibertec.Matias_Albino_Cristopher_Frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import pe.com.cibertec.Matias_Albino_Cristopher_Frontend.dto.BuscarResponseDTO;
import pe.com.cibertec.Matias_Albino_Cristopher_Frontend.dto.BuscarResquestDTO;
import pe.com.cibertec.Matias_Albino_Cristopher_Frontend.viewmodel.BuscarModel;

@Controller
@RequestMapping("/login")

public class BuscarController {

    @Autowired
    RestTemplate restTemplate;


    @GetMapping("/inicio")
    public String inicio(Model model) {
        BuscarModel buscarModel = new BuscarModel("00", "", "","","","","");
        model.addAttribute("buscarModel", buscarModel);
        return "inicio";
    }

    @PostMapping("/buscar")
    public String buscar(@RequestParam("placa") String placa,
                         Model model) {

        if (placa == null || placa.trim().length() == 0) {
            BuscarModel buscarModel = new BuscarModel("01", "Error: de datos", "","","","","");
            model.addAttribute("buscarModel", buscarModel);
            return "inicio";
        }

        //BuscarModel buscarModel = new BuscarModel("00", "", "xxxxx");
        //model.addAttribute("buscarModel", buscarModel);
        //return "principal";


        //invokar servicio de autenticacion

        try {
            String endpoint = "http://localhost:8083/busqueda/buscar";
            BuscarResquestDTO buscarResquestDTO = new BuscarResquestDTO(placa);
            BuscarResponseDTO buscarResponseDTO = restTemplate.postForObject(endpoint,buscarResquestDTO,BuscarResponseDTO.class);

            if(buscarResponseDTO.codigo().equals("00")){
                BuscarModel buscarModel = new BuscarModel("00","",buscarResponseDTO.marca(),buscarResponseDTO.modelo(),buscarResponseDTO.nroasiento(),buscarResponseDTO.precio(),buscarResponseDTO.color());
                model.addAttribute("buscarModel",buscarModel);
                return "principal";
            }
            else{
                BuscarModel buscarModel = new BuscarModel("00", "Error: de datos", "","","","","");
                model.addAttribute("buscarModel", buscarModel);
                return "inicio";
            }

        }catch (Exception e){
            BuscarModel buscarModel = new BuscarModel("99", "Error: Ocurrio un Problema", "","","","","");
            model.addAttribute("buscarModel", buscarModel);
            System.out.println(e.getMessage());
            return "inicio";

        }

    }





}
