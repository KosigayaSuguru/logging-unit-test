package logging.mock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.LogbackException;

public class MyLoggingTest {

	@Test
	public void testLoggingMethod() {

		// 最終的にロギングした時の情報を格納するリスト
		List<ILoggingEvent> logs = Collections.synchronizedList(new ArrayList<>());

		// rootロガーにテスト用のAppenderを追加し、追加するAppenderの中で、ログのイベントを↑のリストに格納していく
		// 追加するAppenderの種類は何でも良いのでとりあえずConsoleにする
		// Loggerはslf4jでなく、logbackのLoggerを使用すること
		Logger logger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		Appender<ILoggingEvent> newAppender = new ConsoleAppender<ILoggingEvent>() {

			// doAppender()の説明（正直呼んでもよくわからんけど。。）
			// https://logback.qos.ch/manual/appenders_ja.html
			//
			// とりあえず、ロギングのイベントが発生する毎に呼ばれているのだと思われる
			@Override
			public void doAppend(ILoggingEvent event) throws LogbackException {
				logs.add(event);
			}
		};

		// ルートロガーにAppenderを登録する
		// ※以降、ルートロガーを使った時に↑で設定した動作が行われるようになる
		logger.addAppender(newAppender);

		MyLogging logging = new MyLogging();
		logging.loggingMethod();

		System.out.println(logs);

		// 最後にルートロガーからAppenderをでタッチしておく
		// ※そうしないとテスト用のAppenderがルートロガーに残りっぱなしになる
		logger.detachAppender(newAppender);
	}
}
