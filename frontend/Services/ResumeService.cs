using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Text.Json;
using System.Threading.Tasks;
using Frontend.Models;

namespace Frontend.Services
{
    public class ResumeService
    {
        private readonly HttpClient _httpClient;

        public ResumeService(HttpClient httpClient)
        {
            _httpClient = httpClient;
        }

        public async Task<List<Resume>> GetResumesAsync(ResumeQuery query)
        {
            var queryString = $"?sex={query.Sex}&experience={query.Experience}&skills={string.Join(",", query.Skills ?? new List<string>())}";
            HttpResponseMessage response = await _httpClient.GetAsync("/api/resumes" + queryString);

            if (response.IsSuccessStatusCode)
            {
                var json = await response.Content.ReadAsStringAsync();
                return JsonSerializer.Deserialize<List<Resume>>(json) ?? new List<Resume>();
            }
            else
            {
                throw new Exception($"Ошибка при получении данных: {response.ReasonPhrase}");
            }
        }
    }
}
