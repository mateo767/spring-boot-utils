package io.github.mateo767.springbootutils.integration.threadname;

import io.github.mateo767.springbootutils.integration.SomeObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = ThreadNameStarter.class)
class ThreadNameTest {

    @Autowired
    private ThreadNameService threadNameService;

    static void main(String[] args) {
        SpringApplication.run(ThreadNameTest.class, args);
    }

    @Test
    void threadNameWithPrefixAndThreadId_shouldRestoreOldThreadName() {
        // given
        var oldName = Thread.currentThread().getName();
        var testThreadName = "7f9fc8db-a9d9-4542-a509-99a13c26829c";
        Thread.currentThread().setName(testThreadName);

        // when
        var serviceThreadName = threadNameService.getThreadName();

        // then
        assertTrue(serviceThreadName.startsWith("Prefix-9"));
        assertEquals(testThreadName, Thread.currentThread().getName());
        Thread.currentThread().setName(oldName);
    }


    @Test
    void threadNameWithPrefixAndThreadId_shouldNotRestoreOldThreadName() {
        // given
        var oldName = Thread.currentThread().getName();

        // when
        var name = threadNameService.getThreadNameDontRestoreName();

        // then
        assertTrue(name.startsWith("Prefix-9"));
        assertTrue(Thread.currentThread().getName().startsWith("Prefix-9"));
        Thread.currentThread().setName(oldName);
    }

    @Test
    void threadNameWithPrefixAndArgumentValuesAndThreadId() {
        assertTrue(threadNameService.getThreadNameWithArguments("sValue", 0)
                .startsWith("Prefix-sValue-0-9"));
    }

    @Test
    void threadNameWithPrefixAndNullArgumentValuesAndThreadId() {
        assertTrue(threadNameService.getThreadNameWithArguments(null, 0)
                .startsWith("Prefix-null-0-9"));
    }

    @Test
    void threadNameWithPrefixAndArgumentFieldAndThreadId() {
        assertTrue(threadNameService.getThreadNameWithArgumentFields(
                        new SomeObject("sValue", -1),
                        new SomeObject("error", 0))
                .startsWith("Prefix-sValue-0-9"));
    }

    @Test
    void threadNameWithPrefixAndNullArgumentFieldAndThreadId() {
        assertTrue(threadNameService.getThreadNameWithArgumentFields(
                        new SomeObject(null, -1),
                        new SomeObject("error", 0))
                .startsWith("Prefix-null-0-9"));
    }
}
