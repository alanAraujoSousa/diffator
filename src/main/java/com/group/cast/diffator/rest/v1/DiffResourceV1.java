package com.group.cast.diffator.rest.v1;

import com.group.cast.diffator.dto.DiffResponseDTO;
import com.group.cast.diffator.service.DiffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

@RestController("/v1/diff")
public class DiffResourceV1 {

    @Autowired
    private DiffService diffService;

    @GetMapping("/{id}")
    public ResponseEntity<DiffResponseDTO> get(@PathVariable("id") Long id) {
        DiffResponseDTO message = diffService.checkDiff(id);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/{id}/left")
    public ResponseEntity<Object> left(@PathVariable("id") Long id, InputStream binaryData) throws IOException {

        byte[] encodedBytes = new byte[binaryData.available()];
        binaryData.read(encodedBytes);

        diffService.createLeftDoc(id, new String(Base64.getDecoder().decode(encodedBytes)));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/right")
    public ResponseEntity<Object> right(@PathVariable("id") Long id, InputStream binaryData) throws IOException {

        byte[] encodedBytes = new byte[binaryData.available()];
        binaryData.read(encodedBytes);

        diffService.createRightDoc(id, new String(Base64.getDecoder().decode(encodedBytes)));
        return ResponseEntity.ok().build();
    }
}
