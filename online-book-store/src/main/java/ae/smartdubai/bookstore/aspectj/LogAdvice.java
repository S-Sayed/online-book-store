package ae.smartdubai.bookstore.aspectj;

import java.time.Duration;
import java.time.LocalTime;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAdvice {
	private static final Logger LOGGER = LoggerFactory.getLogger(LogAdvice.class);

	@Around("@annotation(ae.smartdubai.bookstore.annotation.Log)")
	public Object log(ProceedingJoinPoint point) throws Throwable {
		LocalTime startTime = LocalTime.now();

		String className = point.getSignature().getDeclaringTypeName();
		String methodName = point.getSignature().getName();

		LOGGER.info(className + "." + methodName + " - START");
		Object result = point.proceed();
		LOGGER.info(className + "." + methodName + " - END");

		LocalTime endTime = LocalTime.now();
		Duration duration = Duration.between(startTime, endTime);

		LOGGER.info(className + "." + methodName + " took " + duration.getSeconds() + " seconds");

		return result;
	}
}