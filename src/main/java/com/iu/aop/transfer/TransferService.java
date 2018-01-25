package com.iu.aop.transfer;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

@Service
public class TransferService {
	
	@Inject
	private Transport transport;
	/*
	@Inject
	private CardCheck cardCheck;
	*/
	public void start() {
		transport.subway();
		transport.bus();
		//cardCheck.check();//재사용성을 위해 만들어 놧다고하지만 겁나많->관점지향으로하자
	}

}
