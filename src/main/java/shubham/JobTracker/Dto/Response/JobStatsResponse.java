package shubham.JobTracker.Dto.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobStatsResponse {


        private long total;
        private long applied;
        private long interview;
        private long offer;
        private long rejected;


}
