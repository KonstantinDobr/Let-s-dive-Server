package com.samsung.server.service.Impl;

import com.samsung.server.dao.RecordDao;
import com.samsung.server.domain.Record;
import com.samsung.server.domain.User;
import com.samsung.server.exception.RecordNotFoundException;
import com.samsung.server.exception.UserNotFoundException;
import com.samsung.server.mapper.UserMapper;
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

    @Override
    public Record update(long id, Record record) {
        Optional<Record> optionalRecord = recordDao.findById(id);

        if(!optionalRecord.isPresent()) return null;
        Record newRecord = optionalRecord.get();

        newRecord.setDate(record.getDate());
        newRecord.setPlaceName(record.getPlaceName());
        newRecord.setStartDate(record.getStartDate());
        newRecord.setEndDate(record.getEndDate());
        newRecord.setInformation(record.getInformation());
        newRecord.setDepth(record.getDepth());

        return recordDao.save(newRecord);
    }

    @Override
    public void deleteById(long id) {
        recordDao.deleteById(id);
    }
}
