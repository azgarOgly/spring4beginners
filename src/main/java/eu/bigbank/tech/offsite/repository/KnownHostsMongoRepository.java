package eu.bigbank.tech.offsite.repository;

import eu.bigbank.tech.offsite.model.Host;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KnownHostsMongoRepository extends MongoRepository<Host, String> {
}
