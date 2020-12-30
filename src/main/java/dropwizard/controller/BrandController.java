package dropwizard.controller;


import com.codahale.metrics.annotation.Timed;
import dropwizard.entity.BrandEntity;
import dropwizard.repository.BrandRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/brands")
@Produces(MediaType.APPLICATION_JSON)
public class BrandController {
    private final int defaultSize;
    private final BrandRepository brandRepository;

    public BrandController(int defaultSize, BrandRepository brandRepository) {
        this.defaultSize = defaultSize;
        this.brandRepository = brandRepository;
    }

    @GET
    @Timed
    public List<BrandEntity> getBrands(@QueryParam("size") Optional<Integer> size) {
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return brandRepository.findAll(size.orElse(defaultSize));
    }

    @GET
    @Path("/{id}")
    @Timed
    public BrandEntity getById(@PathParam("id") Long id) {

        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return brandRepository
                .findById(id)
                .orElseThrow(RuntimeException::new);
    }
}
