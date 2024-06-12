package com.samsung.server.service;

import com.samsung.server.domain.Record;

public interface RecordService {
    Record getById(long id);

    Record add(Record record);

    Record update(long id, Record record);

    void deleteById(long id);
}
