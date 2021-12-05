package ru.vadim.quotes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.vadim.quotes.dto.QuoteDto;
import ru.vadim.quotes.mappers.QuoteMapper;
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

    public static final String API_QUOTES = "/api/quotes/";

    public static final String ISIN_QUOTE_ONE = "RU000A0JX0K9";
    public static final String ISIN_QUOTE_TWO = "RU000A0JX0K1";

    public static final BigDecimal BID_QUOTE_ONE = BigDecimal.valueOf(106.3);
    public static final BigDecimal BID_QUOTE_TWO = BigDecimal.valueOf(106.3);

    public static final BigDecimal ASK_QUOTE_ONE = BigDecimal.valueOf(108.5);
    public static final BigDecimal ASK_QUOTE_TWO = BigDecimal.valueOf(108.5);
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private QuotesRepository repository;
    @Autowired
    private ObjectMapper objectMapper;
    private QuoteMapper quoteMapper;

    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }

    @Test
    void getList_thenReturnCorrect() throws Exception {
        mockMvc.perform(post(API_QUOTES)
                        .content(objectMapper.writeValueAsString(prepareQuote()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void givenQuoteByIsin_whenExistingQuote_thenStatus200andQuoteReturned() throws Exception {

        var result = mockMvc.perform(
                        get(API_QUOTES + ISIN_QUOTE_ONE))
                .andExpect(status().isOk())
                .andReturn();
        Assertions.assertEquals(objectMapper.writeValueAsString(prepareQuote()),
                result.getResponse().getContentAsString());

    }

    @Test
    @DisplayName("")
    void givenQuotes_whenGetQuotes_thenGetList() throws Exception {
        Quote quote1 = new Quote(ISIN_QUOTE_ONE, BID_QUOTE_ONE, ASK_QUOTE_ONE);
        Quote quote2 = new Quote(ISIN_QUOTE_TWO, BID_QUOTE_TWO, ASK_QUOTE_TWO);
        quote1.setId(1L);
        quote1.setElvl(BID_QUOTE_ONE);
        quote1.setActual(true);
        quote2.setId(2L);
        quote2.setElvl(BID_QUOTE_TWO);
        quote2.setActual(true);

        mockMvc.perform(get(API_QUOTES))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        objectMapper.writeValueAsString(Arrays.asList(quote1, quote2)))
                );
    }

    private QuoteDto prepareQuote() {

        return QuoteDto.builder()
                .isin(ISIN_QUOTE_ONE)
                .bid(BID_QUOTE_ONE)
                .ask(ASK_QUOTE_ONE)
                .elvl(BID_QUOTE_ONE)
                .build();
    }
}
