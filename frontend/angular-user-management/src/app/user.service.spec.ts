import { TestBed, inject } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { UserService } from './user.service';

describe('UserService', () => {
  let userService: UserService;
  let httpTestingController: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [UserService]
    });

    userService = TestBed.inject(UserService);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpTestingController.verify();
  });

  it('should be created', () => {
    expect(userService).toBeTruthy();
  });

  it('should create a new user', () => {
    const userData = {  };

    userService.createNewUser(userData).subscribe(response => {
      expect(response).toBeTruthy();

    });

    const req = httpTestingController.expectOne('http://localhost:8080/api/alianza');
    expect(req.request.method).toBe('POST');
    req.flush({  });
  });

  it('should search user by shared key', () => {
    const sharedKey = 'exampleKey';

    userService.searchUserBySharedKey(sharedKey).subscribe(response => {
      expect(response).toBeTruthy();
    
    });

    const req = httpTestingController.expectOne(`http://localhost:8080/api/alianza/${sharedKey}`);
    expect(req.request.method).toBe('GET');
    req.flush({  });
  });

  it('should get all users', () => {
    userService.getAllUsers().subscribe(response => {
      expect(response).toBeTruthy();

    });

    const req = httpTestingController.expectOne('http://localhost:8080/api/alianza');
    expect(req.request.method).toBe('GET');
    req.flush({  });
  });
});
