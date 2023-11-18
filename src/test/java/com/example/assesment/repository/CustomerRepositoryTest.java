package com.example.assesment.repository;

import com.example.assesment.entity.Customer;
import com.example.assesment.entity.TransactionRecord;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

//@RunWith(SpringRunner.class)
//@SpringBootTest
@ContextConfiguration("../DBUnitConfig/repositories-test-context.xml")
@ActiveProfiles({"test"})
public class CustomerRepositoryTest {

    @BeforeTestClass
    public static void beforeClass()
    {
        System.setProperty("defaultWebServer", "http://miu.edu.com");
        System.setProperty("AUTH_METHOD", "SERVER");
    }
    @Mock
    private CustomerRepository customerRepository;

    @Test
    public void testMethod()
    {
        Customer customer = getCustomerMock(1l, "Giom");

        assertEquals(2, customer.getTransactionRecords().size());
    }
    private TransactionRecord getTransactionMock(long id, LocalDate date, double amount)
    {
        TransactionRecord transactionRecord = new TransactionRecord();
        when(transactionRecord.getId()).thenReturn(id);
        when(transactionRecord.getDate()).thenReturn(date);
        when(transactionRecord.getAmount()).thenReturn(amount);

        return transactionRecord;
    }

    private Customer getCustomerMock(long id, String name)
    {
        Customer customer = new Customer();
        when(customer.getId()).thenReturn(id);
        when(customer.getName()).thenReturn(name);
        List<TransactionRecord> transactionRecordList = Arrays.asList(
                getTransactionMock(1L, LocalDate.now(), 33.4),
                getTransactionMock(2L, LocalDate.now(), 34.4)
        );
        when(customer.getTransactionRecords()).thenReturn(transactionRecordList);

        doReturn(id).when(customer);

        return customer;
    }


}
