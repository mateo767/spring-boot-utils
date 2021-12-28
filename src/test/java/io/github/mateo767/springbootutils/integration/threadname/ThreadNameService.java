package io.github.mateo767.springbootutils.integration.threadname;

import io.github.mateo767.springbootutils.integration.SomeObject;
import io.github.mateo767.springbootutils.threadname.ThreadName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
class ThreadNameService {

    @ThreadName("Prefix")
    String getThreadName() {
        log.info(Thread.currentThread().getName());
        return Thread.currentThread().getName();
    }

    @ThreadName(prefix = "Prefix", expressions = {"#arg1", "#arg2"})
    @SuppressWarnings({"SameParameterValue", "unused"})
    String getThreadNameWithArguments(String arg1, int arg2) {
        log.info(Thread.currentThread().getName());
        return Thread.currentThread().getName();
    }

    @ThreadName(prefix = "Prefix", expressions = {"#arg1.sField", "#arg2.iField"})
    @SuppressWarnings("unused")
    String getThreadNameWithArgumentFields(SomeObject arg1, SomeObject arg2) {
        log.info(Thread.currentThread().getName());
        return Thread.currentThread().getName();
    }
}
