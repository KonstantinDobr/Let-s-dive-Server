package com.samsung.server.service.Impl;

import com.samsung.server.dao.RecordDao;
import com.samsung.server.domain.Record;
import com.samsung.server.exception.RecordNotFoundException;
import com.samsung.server.exception.UserNotFoundException;
import com.samsung.server.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService {

    private final RecordDao recordDao;

    @Override
    public Record getById(long id) {
        Optional<Record> optionalRecord = recordDao.findById(id);

        if(!optionalRecord.isPresent()) throw new RecordNotFoundException("Record with Id " + id + " not found");

        return optionalRecord.get();
    }

    @Override
    public Record add(Record record) {
        return recordDao.save(record);
    }
}
