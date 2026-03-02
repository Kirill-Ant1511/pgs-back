package pal.comp.pgsbackend.controller;


import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pal.comp.pgsbackend.dto.machine.RequestMachineDto;
import pal.comp.pgsbackend.dto.machine.ResponseMachineDto;
import pal.comp.pgsbackend.services.MachineService;

import java.util.List;

@RestController
@Controller
@RequestMapping("/machine")
public class MachineController {
    private MachineService machineService;
    public MachineController(MachineService machineService) {
        this.machineService = machineService;
    }


    @GetMapping
    public List<ResponseMachineDto> getMachines() {
        return this.machineService.findAll();
    }

    @PostMapping
    public ResponseMachineDto saveMachine(@RequestBody @Valid RequestMachineDto requestDto) {
        return this.machineService.create(requestDto);
    }


    @PutMapping("/{id}")
    public ResponseMachineDto update(@PathVariable Long id, @RequestBody RequestMachineDto requestDto) {
        return this.machineService.update(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteMachine(@PathVariable Long id) {
        this.machineService.delete(id);
    }


}
