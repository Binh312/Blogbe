package com.web.mapper;

import com.web.dto.request.BlogRequest;
import com.web.dto.request.DocumentRequest;
import com.web.entity.Blog;
import com.web.entity.Document;
import org.springframework.stereotype.Service;

@Service
public class DocumentMapper {

    public Document convertRequestToDocument(DocumentRequest request){
        Document document = new Document();
        document.setId(request.getId());
        document.setName(request.getName());
        document.setDescription(request.getDescription());
        document.setImage(request.getImage());
        document.setLinkFile(request.getLinkFile());

        return document;
    }
}
