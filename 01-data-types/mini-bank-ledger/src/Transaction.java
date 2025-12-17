
import java.math.BigDecimal;

public record Transaction(
        char type,
        long accountId,
        BigDecimal amount,
        long timestampMillis,
        String note
) { }