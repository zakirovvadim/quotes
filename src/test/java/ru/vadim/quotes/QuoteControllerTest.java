package ru.vadim.quotes;

import org.springframework.test.context.jdbc.Sql;
import ru.vadim.quotes.controller.QuoteController;
import ru.vadim.quotes.model.Quote;
import ru.vadim.quotes.repository.QuotesRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.vadim.quotes.service.QuoteService;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@Sql(value = {"/create-quote-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-quote-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class QuoteControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private QuotesRepository repository;
    @Autowired
    private ObjectMapper objectMapper;

    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }

//    private Quote createTestQuote(String isin, BigDecimal bid, BigDecimal ask) {
//        Quote quote = new Quote(isin, bid, ask);
//        return repository.save(quote);
//    }


    @Test
    void getList_thenReturnCorrect() throws Exception {
        Quote quote = new Quote("RU000A0JX0K2", new BigDecimal("106.2"), new BigDecimal("107.4"));

        mockMvc.perform(post("/api/quotes").content(objectMapper.writeValueAsString(quote)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void givenQuoteByIsin_whenExistingQuote_thenStatus200andQuoteReturned() throws Exception {
        String isin = "RU000A0JX0K9";

                mockMvc.perform(
                get("/api/quotes/{isin}", isin))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isin").value(isin))
                .andExpect(jsonPath("$.bid").value(106.3))
                .andExpect(jsonPath("$.ask").value(108.5))
                .andExpect(jsonPath("$.elvl").value(106.3)
                );

    }

    @Test
    void givenQuotes_whenGetQuotes_thenGetList() throws Exception {
        Quote quote1 = new Quote("RU000A0JX0K9", new BigDecimal("106.3"), new BigDecimal("108.5"));
        Quote quote2 = new Quote("RU000A0JX0K1", new BigDecimal("105.6"), new BigDecimal("107.8"));
        quote1.setId(1L);
        quote1.setElvl(new BigDecimal(106.3));
        quote1.setActual(true);
        quote2.setId(2L);
        quote2.setElvl(new BigDecimal(105.6));
        quote2.setActual(true);

        mockMvc.perform(
                get("/api/quotes"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(quote1, quote2)))
                );
    }

}
