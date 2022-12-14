package io.sample.attendance.generator.docs;

import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static io.sample.attendance.config.docs.ApiDocumentUtils.createDocumentByResourceSnippet;
import static io.sample.attendance.config.docs.ApiDocumentUtils.createDocumentBySnippets;
import static io.sample.attendance.config.docs.ApiDocumentUtils.format;
import static io.sample.attendance.generator.docs.AttendanceFieldDescriptor.attendanceResponseFieldWithPath;
import static io.sample.attendance.generator.docs.common.CommonFieldDescriptor.commonErrorFieldWithPath;
import static io.sample.attendance.generator.docs.common.CommonFieldDescriptor.commonPaginationFieldWithPath;
import static io.sample.attendance.generator.docs.common.CommonFieldDescriptor.invalidErrorFieldWithPath;
import static io.sample.attendance.generator.docs.common.CommonHeaderSnippetGenerator.defaultRequestHeaderDescriptors;
import static io.sample.attendance.generator.docs.common.CommonHeaderSnippetGenerator.defaultRequestHeaderSnippet;
import static io.sample.attendance.generator.docs.common.CommonHeaderSnippetGenerator.defaultResponseHeaderDescriptor;
import static io.sample.attendance.generator.docs.common.CommonHeaderSnippetGenerator.defaultResponseHeaderSnippet;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.relaxedResponseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.FieldDescriptor;

public class AttendanceDocsGenerator {
    public static RestDocumentationResultHandler saveAttendanceDocument() {
        return createDocumentByResourceSnippet(
            resource(
                ResourceSnippetParameters.builder()
                    .summary("?????? ?????? API")
                    .description("POST ????????? ???????????? ????????? ????????? ??? ????????????.")
                    .requestHeaders(defaultRequestHeaderDescriptors())
                    .responseHeaders(defaultResponseHeaderDescriptor())
                    .requestFields(
                        fieldWithPath("startAt").description("?????? ?????? ??????").attributes(format("YYYY-MM-DD hh:mm:ss")),
                        fieldWithPath("endAt").description("?????? ?????? ??????").attributes(format("YYYY-MM-DD hh:mm:ss"))
                    )
                    .responseFields(
                        attendanceResponseFieldWithPath()
                    )
                    .build()
            )
        );
    }

    public static RestDocumentationResultHandler emptyRegisterAttendanceRequestDocument() {
        return createDocumentBySnippets(
            defaultRequestHeaderSnippet(),
            defaultResponseHeaderSnippet(),
            responseFields(
                commonErrorFieldWithPath()
            )
        );
    }

    public static RestDocumentationResultHandler invalidRegisterAttendanceRequestDocument() {
        return createDocumentBySnippets(
            defaultRequestHeaderSnippet(),
            defaultResponseHeaderSnippet(),
            requestFields(
                fieldWithPath("startAt").description("?????? ?????? ??????").attributes(format("YYYY-MM-DD hh:mm:ss")),
                fieldWithPath("endAt").description("?????? ?????? ??????").attributes(format("YYYY-MM-DD hh:mm:ss"))
            ),
            relaxedResponseFields(
                Stream.concat(
                    commonErrorFieldWithPath().stream(),
                    invalidErrorFieldWithPath().stream()
                ).collect(Collectors.toList())
            )
        );
    }

    public static RestDocumentationResultHandler getAnAttendanceDocument() {
        return createDocumentByResourceSnippet(
            resource(
                ResourceSnippetParameters.builder()
                    .summary("?????? ?????? API")
                    .description("GET ????????? ???????????? ????????? ?????? ??? ??? ????????????.")
                    .requestHeaders(defaultRequestHeaderDescriptors())
                    .responseHeaders(defaultResponseHeaderDescriptor())
                    .pathParameters(
                        parameterWithName("id").description("?????? ??????")
                    )
                    .responseFields(
                        attendanceResponseFieldWithPath()
                    )
                    .build()
            )
        );
    }

    public static RestDocumentationResultHandler notFoundAttendanceRequestDocument() {
        return createDocumentBySnippets(
            defaultRequestHeaderSnippet(),
            defaultResponseHeaderSnippet(),
            pathParameters(
                parameterWithName("id").description("?????? ??????")
            ),
            responseFields(
                commonErrorFieldWithPath()
            )
        );
    }

    public static RestDocumentationResultHandler getAttendancesDocument() {
        return createDocumentByResourceSnippet(
            resource(
                ResourceSnippetParameters.builder()
                    .summary("??????????????? ?????? ?????? ?????? API")
                    .description("GET ????????? ???????????? ??????????????? ?????? ????????? ????????? ??? ????????????.")
                    .requestHeaders(defaultRequestHeaderDescriptors())
                    .responseHeaders(defaultResponseHeaderDescriptor())
                    .requestParameters(
                        parameterWithName("yearMonth").description("?????? ??????").attributes(format("YYYY-MM"))
                    )
                    .responseFields(
                        Stream.concat(
                            commonPaginationFieldWithPath().stream(),
                            Stream.of(
                                fieldWithPath("elements[*].id").description("?????? ??????"),
                                fieldWithPath("elements[*].startAt").description("?????? ?????? ??????"),
                                fieldWithPath("elements[*].endAt").description("?????? ?????? ??????"),
                                fieldWithPath("elements[*].workDuration.hour").description("?????? ?????? ??????"),
                                fieldWithPath("elements[*].workDuration.minute").description("?????? ?????? ???"),
                                fieldWithPath("elements[*].basicPay").description("?????? ??????"),
                                fieldWithPath("elements[*].totalPay").description("?????? ??????"),
                                fieldWithPath("elements[*].extraWorks[*]").description("?????? ?????? ??????"),
                                fieldWithPath("elements[*].extraWorks[*].id").description("?????? ?????? ??????"),
                                fieldWithPath("elements[*].extraWorks[*].startAt").description("?????? ?????? ?????? ??????"),
                                fieldWithPath("elements[*].extraWorks[*].endAt").description("?????? ?????? ?????? ??????"),
                                fieldWithPath("elements[*].extraWorks[*].workDuration.hour").description("?????? ?????? ?????? ??????"),
                                fieldWithPath("elements[*].extraWorks[*].workDuration.minute").description("?????? ?????? ?????? ???"),
                                fieldWithPath("elements[*].extraWorks[*].extraWorkType").description("?????? ?????? ??????"),
                                fieldWithPath("elements[*].extraWorks[*].extraPay").description("?????? ??????")
                            )
                        ).collect(Collectors.toList())
                    )
                    .build()
            )
        );
    }
}

class AttendanceFieldDescriptor {
    public static List<FieldDescriptor> attendanceResponseFieldWithPath() {
        return Arrays.asList(
            fieldWithPath("id").description("?????? ??????"),
            fieldWithPath("startAt").description("?????? ?????? ??????"),
            fieldWithPath("endAt").description("?????? ?????? ??????"),
            fieldWithPath("workDuration.hour").description("?????? ?????? ??????"),
            fieldWithPath("workDuration.minute").description("?????? ?????? ???"),
            fieldWithPath("basicPay").description("?????? ??????"),
            fieldWithPath("totalPay").description("?????? ??????"),
            fieldWithPath("extraWorks[*].id").description("?????? ?????? ??????"),
            fieldWithPath("extraWorks[*].startAt").description("?????? ?????? ?????? ??????"),
            fieldWithPath("extraWorks[*].endAt").description("?????? ?????? ?????? ??????"),
            fieldWithPath("extraWorks[*].workDuration.hour").description("?????? ?????? ?????? ??????"),
            fieldWithPath("extraWorks[*].workDuration.minute").description("?????? ?????? ?????? ???"),
            fieldWithPath("extraWorks[*].extraWorkType").description("?????? ?????? ?????? ??????"),
            fieldWithPath("extraWorks[*].extraPay").description("?????? ??????")
        );
    }
}
