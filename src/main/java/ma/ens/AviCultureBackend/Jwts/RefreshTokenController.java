package ma.ens.AviCultureBackend.Jwts;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/token")
public class RefreshTokenController {

	private final JwtServiceImpl jwtsService;

	@PostMapping(path = "/refresh")
	public void getAccessTokenByRefreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return;
	}
}
