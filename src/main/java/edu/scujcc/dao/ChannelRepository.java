package edu.scujcc.dao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import edu.scujcc.model.Channel;

import java.util.List;

public interface ChannelRepository extends MongoRepository<Channel, String>{
        public List<Channel> findByQuality(String q);
        public List<Channel> findByTitle(String t);
        public Channel findFirstByQuality(String q);
        public List<Channel> findByCommentsNull();

}
