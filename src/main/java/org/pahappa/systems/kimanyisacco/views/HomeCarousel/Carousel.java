package org.pahappa.systems.kimanyisacco.views.HomeCarousel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class Carousel implements Serializable {

    private List<ImageModel> images;

    public Carousel() {
        images = new ArrayList<>();
        images.add(new ImageModel("/resources/images/buy.jpg", "Buy goods and look for market for our SACCO members"));
        images.add(new ImageModel("/resources/images/celebrate.jpeg", "Celebrate together, Achieve your dream ceremony!"));
        images.add(new ImageModel("/resources/images/Deposit.jpeg", "Deposit and withdraw money from your sacco account at the place of your convenience"));
        images.add(new ImageModel("/resources/images/Discussion.jpg", "Money Discussions"));
        images.add(new ImageModel("/resources/images/Loans.jpg", "Apply for loans, get money instantly and finish your work without any delay"));
        images.add(new ImageModel("/resources/images/work.jpeg", "Working together to achieve the best"));
    }

    public List<ImageModel> getImages() {
        return images;
    }

    public static class ImageModel {
        private String imageUrl;
        private String description;

        public ImageModel(String imageUrl, String description) {
            this.imageUrl = imageUrl;
            this.description = description;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public String getDescription() {
            return description;
        }
    }
}