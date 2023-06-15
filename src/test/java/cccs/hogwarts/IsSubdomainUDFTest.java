package cccs.hogwarts;

import static org.junit.Assert.assertEquals;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.Test;
import java.util.Arrays;
import cccs.hogwarts.domainutils.IsSubdomainUDF;

@RunWith(Parameterized.class)
public class IsSubdomainUDFTest {
    private String givenDomain;
    private boolean expectedIsSubdomain;

    @Parameters(name = "{index}: isSubdomain({0})={1}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
                // Regular domains, no exception
                { "www.google.com", true }, { "google.com", false },
                // blogspot.com is a special case, there might be others
                { "foo.blogspot.com", true }, { "blogspot.com", false },
                // Second-level domains (2LD or SLD)
                { "ca", false }, { "gc.ca", false },
                { "domain.gc.ca", false }, { "sub.domain.gc.ca", true }, { "sub2.sub1.domain.gc.ca", true },
                // Invalid domains
                { "https://nh.gov/#c8j88xs82wkv5", false }
        });
    }

    public IsSubdomainUDFTest(String givenDomain, boolean expectedIsSubdomain) {
        this.givenDomain = givenDomain;
        this.expectedIsSubdomain = expectedIsSubdomain;
    }

    @Test
    public void testDomainIsSubdomain() throws Exception {
        IsSubdomainUDF udf = new IsSubdomainUDF();
        assertEquals(expectedIsSubdomain, udf.call(givenDomain));
    }

    @Test
    public void testUppercaseDomainIsSubdomain() throws Exception {
        IsSubdomainUDF udf = new IsSubdomainUDF();
        assertEquals(expectedIsSubdomain, udf.call(givenDomain.toUpperCase()));
    }
}
