package com.careerconnect.companyms.company.messaging;

import com.careerconnect.companyms.company.CompanyService;
import com.careerconnect.companyms.company.dto.ReviewMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ReviewMessageConsumer {

    private final CompanyService companyService;


    public ReviewMessageConsumer(CompanyService companyService) {
        this.companyService = companyService;
    }
    @RabbitListener(queues = "companyRatingQueue")
    public void consumeMessage(ReviewMessage message) {
        companyService.updateCompany(message);
    }
}
