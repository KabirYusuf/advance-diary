import data.repositories.DiaryRepo;
import data.repositories.UserRepo;
import data.repositories.impl.DiaryRepoImpl;
import data.repositories.impl.UserRepoImpl;

public class Main {
    public static void main(String[] args) {
        UserRepo userRepo = new UserRepoImpl();
        userRepo.getAllUsers();
        DiaryRepo diaryRepo = new DiaryRepoImpl();
        diaryRepo.getAllDiaryContent();
    }
}
