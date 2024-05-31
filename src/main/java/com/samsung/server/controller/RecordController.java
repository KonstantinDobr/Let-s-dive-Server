package com.samsung.server.controller;

import com.samsung.server.domain.Record;
import com.samsung.server.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/server/v1/record")
public class RecordController {

    private final RecordService recordService;

    @GetMapping("/{id}")
    public Record getById(@PathVariable long id) {
        return recordService.getById(id);
    }

    @PostMapping()
    public Record add(@RequestBody Record record) {
        return recordService.add(record);
    }
}
