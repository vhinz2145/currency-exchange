// package com.example.currencyexchange.repository;

// import com.example.currencyexchange.model.ExchangeRate;
// import org.springframework.jdbc.core.RowMapper;
// import java.sql.ResultSet;
// import java.sql.SQLException;

// public class ExchangeRateRowMapper implements RowMapper<ExchangeRate> {
//     @Override
//     public ExchangeRate mapRow(@SuppressWarnings("null") ResultSet rs, int rowNum) throws SQLException {
//         ExchangeRate exchangeRate = new ExchangeRate();
//         exchangeRate.setSourceCurrency(rs.getString("source_currency"));
//         exchangeRate.setTargetCurrency(rs.getString("target_currency"));
//         exchangeRate.setCutOffDate(rs.getDate("cut_off_date").toLocalDate());
//         exchangeRate.setExchangeRate(rs.getBigDecimal("exchange_rate"));
//         return exchangeRate;
//     }
// }
