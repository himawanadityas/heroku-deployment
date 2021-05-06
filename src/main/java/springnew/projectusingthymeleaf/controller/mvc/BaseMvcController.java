package springnew.projectusingthymeleaf.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class BaseMvcController {

    @GetMapping("dashboard")
    public String dashboard() {
        return "dashboard/index";
    }

    @GetMapping("biodata")
    public String biodata() {
        return "biodata/index";
    }

    @GetMapping("mahasiswa")
    public String mahasiswa() {
        return "mahasiswa/index";
    }

    @GetMapping("dosen")
    public String dosen() {
        return "dosen/index";
    }

    @GetMapping("nilaiEdit")
    public String nilai() {
        return "nilai/index";
    }

    @GetMapping("agama")
    public String agama() {
        return "agama/index";
    }

    @GetMapping("nilai")
    public String nilai2() {
        return "nilai/index2";
    }

    @GetMapping("fileupload")
    public String file() {
        return "tugas1/index";
    }

}
